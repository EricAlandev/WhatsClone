'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import HeaderChatPage from "@/typescript/components/pages/Chat/HeaderChat";
import HeaderSelectedChat from "@/typescript/components/pages/Chat/HeaderSelectedChat";
import RenderMessages from "@/typescript/components/pages/Chat/RenderMessages";
import SendMessage from "@/typescript/components/pages/Chat/SendMessage";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ChatPage(){
    
    const [header, setHeader] = useState<string>();
    
    //define what type of header
    // Options
    // Header -> normal header
    // HeaderSelect -> header for when some message is selected;
    const [headerSelected, setHeaderSelected] = useState<string>("hidden");

    //array with the selectedIds
    const [selectedId, setSelectedIds] = useState<number[]>([]);

    const {id} = useParams();

    const {callMessages, sendMessage, messages} = useChatUseCase();
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
                <HeaderChatPage
                    hiddenOrNot={header}
                />
                <HeaderSelectedChat
                    hiddenOrNot={headerSelected}
                    back={() => {
                        setHeader("");
                        setHeaderSelected("hidden");
                        setSelectedIds([]);
                        arraySelectedID = [];
                    }}
                />

                <RenderMessages
                    idOfLoggedUser={user?.id}
                    chats={messages}
                    options={(onOrOf, idMessage) => {
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
            </AuthorizationComponent>
        </div>
    )
}