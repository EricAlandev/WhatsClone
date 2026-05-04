'use client'

import { ChatType } from "@/typescript/types/ChatType"
import ChatSke from "../Chat/ChatSke";

type RenderChat = {
    chats: ChatType[] | []
}

export default function RenderChats({chats}: RenderChat){

    return(
        <div
            className="w-[90vw] min-h-screen mx-auto"
        >
            {chats?.length > 0 ?(
                chats?.map((c) => (
                    <ChatSke
                        idUser={c?.idUserMessage}
                        name={c?.name}
                        message={c?.lastMessage?.message}
                        status={c?.lastMessage?.status}
                        time={c?.lastMessage?.time}
                    />
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