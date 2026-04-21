'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import Header from "@/typescript/components/headers/Header";
import RenderChats from "@/typescript/components/pages/homepage/RenderChats";


export default function HomepageUser(){


    return(
        <div
            className="flex flex-col pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <Header/>
                <RenderChats
                    chats={"/"}
                />
            </AuthorizationComponent>
        </div>
    )
}