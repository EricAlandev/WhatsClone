'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import Header from "@/typescript/components/headers/Header";
import RenderChats from "@/typescript/components/pages/homepage/RenderChats";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { useEffect } from "react";


export default function HomepageUser(){

    const {friendsFinded, SearchFriends , callAllChats} = useChatUseCase();

    const {token} = useAuth();
    
    useEffect(() => {
        if(token){
            callAllChats(token)
        }
    }, [token]);

    return(
        <div
            className="flex flex-col pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <Header
                    onSearch={SearchFriends}
                    token={token}
                />
                
                <RenderChats
                    chats={friendsFinded}
                />
            </AuthorizationComponent>
        </div>
    )
}