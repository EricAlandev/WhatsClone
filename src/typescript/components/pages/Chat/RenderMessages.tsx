'use client'

import { ChatType } from "@/typescript/types/ChatType"
import SkeListValue from "../../skeletons/SkeListValue";
import SkeMessage from "./Message";

type RenderChat = {
    chats: ChatType[] | [],
    idOfLoggedUser: number
}

export default function RenderMessages({chats, idOfLoggedUser}: RenderChat){

    console.log(chats);

    return(
        <div
            className="w-[90vw] h-[90vh] h-[90vh]  mx-auto overflow-y-auto"
        >
            <div
                className="flex flex-col"
            >
                {chats?.length > 0 &&(
                    chats?.map((c, index) => {
                        //message from user or not
                        let messageFromUser = false
                        if(idOfLoggedUser === c?.idUserMessage){
                            messageFromUser = true         
                        }
                        
                        return(
                            <SkeMessage
                            key={c.id}
                            idMessage={c.id}
                            message={c?.message}
                            time={c?.time}
                            messageFromLoggedUser={messageFromUser}
                            status={c?.status}
                        />
                        )
                    })
                )}
            </div>
        </div>
    )
}