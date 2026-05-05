'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import DeletePopuP from "@/typescript/components/pages/Chat/DeletePopUp";
import EditMessagePopUp from "@/typescript/components/pages/Chat/EditMessagePopUp";
import FixedPopUp from "@/typescript/components/pages/Chat/FixedPopUp";
import HeaderChatPage from "@/typescript/components/pages/Chat/HeaderChat";
import HeaderSelectedChat from "@/typescript/components/pages/Chat/HeaderSelectedChat";
import RenderMessages from "@/typescript/components/pages/Chat/RenderMessages";
import SendMessage from "@/typescript/components/pages/Chat/SendMessage";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { selectedIds } from "@/typescript/types/ChatType";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ChatPage(){
    
    //define what type of header
    // Options
    // Header -> normal header
    // HeaderSelect -> header for when some message is selected;
    const [header, setHeader] = useState<string>();
    const [headerSelected, setHeaderSelected] = useState<string>("hidden");

    //array with the selectedIds
    const [selectedId, setSelectedIds] = useState<selectedIds[]>([]);

    const [isModalDelete, setIsModalDelete] = useState<boolean>(false);
    const [isModalEdit, setIsModalEdit] = useState<boolean>(false);
    const [isModalFixed, setIsModalFixed] = useState<boolean>(false);


    const {id} = useParams();

    //database calls
    const {callMessages, sendMessage, deleteMessage,changeMessage,fixedMessage, messages} = useChatUseCase();
    const {user, token} = useAuth();

    useEffect(() => {
            if(id){
                callMessages(id, token);
            }
    }, [id, token, callMessages]);

    return(
        <div
            className="flex flex-col pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                {/*Normal header */}
                <HeaderChatPage
                    hiddenOrNot={header}
                />

                {/*Header when at least one message is selected */}
                <HeaderSelectedChat
                    hiddenOrNot={headerSelected}
                    back={() => {
                        setHeader("");
                        setHeaderSelected("hidden");
                        setSelectedIds([]);
                    }}
                    deleteMessages={() => {
                        setIsModalDelete(true);
                    }}
                    quantityMessages={selectedId}
                    edit={() => {
                        setIsModalEdit(true);
                    }}
                    putFixado={() => {
                        setIsModalFixed(true)
                    }}
                    
                />

                <RenderMessages
                    idOfLoggedUser={user?.id}
                    chats={messages}
                    options={(onOrOf, idMessage, message, time , status) => {
                        //define if the header is selected and wish is chosed;
                        if(onOrOf === true){
                            setHeader("hidden");
                            setHeaderSelected("");
                            
                            //pass the id,message, time from message
                            setSelectedIds(prev => [...prev, {id: idMessage, message: message, time: time, status: status}]);
                        }
                        else{
                            setHeader("");
                            setHeaderSelected("hidden");
                        }
                    }}
                    HeaderSelected={headerSelected}
                    selectedIds={selectedId}
                />

                <SendMessage
                    send={(message) => {
                       if(id && token){
                            sendMessage(id ,token, message)
                       }
                    }}

                />

                {/*POPUPS */}
                <DeletePopuP
                    open={isModalDelete}
                    setIsModalDelete={setIsModalDelete}
                    deleteMessage={() => {
                        if(token){
                            deleteMessage(selectedId, token, id);
                            setIsModalDelete(false);
                            setSelectedIds([]);

                            //set the header to the normal
                            setHeader("");
                            setHeaderSelected("hidden");
                        }
                    }}
                />

                <EditMessagePopUp
                    open={isModalEdit}
                    setIsModalEdit={setIsModalEdit}
                    selectedIds={selectedId}
                    edit={(message) => {
                        if(token){
                            changeMessage(selectedId, token, id, message)
                            setIsModalEdit(false)
                            setSelectedIds([]);

                            //set the header to the normal
                            setHeader("");
                            setHeaderSelected("hidden");
                        }
                    }}
                />

                <FixedPopUp
                    openPopUp={isModalFixed}
                    setIsModalFixed={setIsModalFixed}
                    fixMessage={(timeToFix) => {
                        if(token && id){
                            fixedMessage(selectedId, token, id);
                        }
                    }}
                />
            </AuthorizationComponent>
        </div>
    )
}