'use client'

import { ChatType } from "@/typescript/types/ChatType"
import SkeListValue from "../../skeletons/SkeListValue";

type RenderChat = {
    chats: ChatType[] | []
}

export default function RenderMessages({chats}: RenderChat){

    return(
        <div
            className="w-[90vw] h-[90vh] max-h-[90vh]  mx-auto overflow-y-auto"
        >
            {chats?.length > 0 &&(
                chats?.map((c) => (
                    <SkeListValue
                        type="chat"
                        idUser={c?.id}
                        name={c?.name}
                    />
                ))
            )}
        </div>
    )
}