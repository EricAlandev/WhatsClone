'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import HeaderChatPage from "@/typescript/components/pages/Chat/HeaderChat";
import RenderMessages from "@/typescript/components/pages/Chat/RenderMessages";
import SendMessage from "@/typescript/components/pages/Chat/SendMessage";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { useParams } from "next/navigation";
import { useEffect } from "react";

export default function ChatPage(){

    const {id} = useParams();

    const {callMessages, sendMessage, messages} = useChatUseCase();
    const {token} = useAuth();


    console.log("inside of id page");
    useEffect(() => {
            console.log("Inside of the token and id", id);
            if(id){
                callMessages(id, token);
            }
    }, [id, token, callMessages]);

    return(
        <div
            className="flex flex-col pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <HeaderChatPage/>
                <RenderMessages
                    chats={messages}
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