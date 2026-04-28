'use client'

import { ChatType } from "@/typescript/types/ChatType"
import SkeListValue from "../../skeletons/SkeListValue";

type RenderChat = {
    chats: ChatType[] | [],
    idOfLoggedUser: number
}

export default function RenderMessages({chats, idOfLoggedUser}: RenderChat){

    return(
        <div
            className="w-[90vw] h-[90vh] max-h-[90vh]  mx-auto overflow-y-auto"
        >
            {chats?.length > 0 &&(
                chats?.map((c) => {
                    //message from user or not
                    let messageFromUser = false
                    if(idOfLoggedUser === c?.id){
                        messageFromUser = true         
                    }

                    return(
                        <SkeListValue
                        type="message"
                        idUser={c?.idUserMessage}
                        message={c?.message}
                        name={c?.name}
                        messageFromLoggedUser={messageFromUser}
                    />
                    )
                })
            )}
        </div>
    )
}