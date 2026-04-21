'use client'

import { ChatType } from "@/typescript/types/ChatType"


export default function RenderChats(chats: ChatType[]){

    return(
        <div
            className="w-[90vw] min-h-screen mx-auto"
        >
            {chats?.length > 0 ?(
                chats.map((c) => (
                    <p>oi</p>
                ))
            ) : (
                <p
                    className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 mt-3 text-[#F1F1F1]"
                >
                    Without any converse yet.
                </p>
            )}
        </div>
    )
}