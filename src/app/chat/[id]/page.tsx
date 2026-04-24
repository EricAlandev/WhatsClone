'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import HeaderChatPage from "@/typescript/components/pages/Chat/HeaderChat";
import RenderMessages from "@/typescript/components/pages/Chat/RenderMessages";
import SendMessage from "@/typescript/components/pages/Chat/SendMessage";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { useSearchParams } from "next/navigation";
import { useEffect } from "react";

export default function ChatPage(){

    const useParams = useSearchParams();
    const id = useParams.get("id");

    const {callMessages, messages} = useChatUseCase();
    const {token} = useAuth();

    useEffect(() => {
        if(token && id){
            callMessages(id,token);
        }
    }, []);

    return(
        <div
            className="flex flex-col pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <HeaderChatPage/>
                <RenderMessages
                    chats={messages}
                />
                <SendMessage/>
            </AuthorizationComponent>
        </div>
    )
}