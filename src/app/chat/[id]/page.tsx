'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import DeletePopuP from "@/typescript/components/pages/Chat/DeletePopUp";
import HeaderChatPage from "@/typescript/components/pages/Chat/HeaderChat";
import HeaderSelectedChat from "@/typescript/components/pages/Chat/HeaderSelectedChat";
import RenderMessages from "@/typescript/components/pages/Chat/RenderMessages";
import SendMessage from "@/typescript/components/pages/Chat/SendMessage";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
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
    const [selectedId, setSelectedIds] = useState<number[]>([]);

    const [isModalDelete, setIsModalDelete] = useState<boolean>(false);

    const {id} = useParams();

    const {callMessages, sendMessage, deleteMessage, messages} = useChatUseCase();
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
                />

                <RenderMessages
                    idOfLoggedUser={user?.id}
                    chats={messages}
                    options={(onOrOf, idMessage) => {
                        //define if the header is selected and wish is chosed;
                        if(onOrOf === true){
                            setHeader("hidden");
                            setHeaderSelected("");
                            
                            setSelectedIds(prev => [...prev, idMessage]);
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
                        }
                    }}
                />
            </AuthorizationComponent>
        </div>
    )
}