'use client'

import { ChatType } from "@/typescript/types/ChatType"
import SkeMessage from "./Message";

type RenderChat = {
    chats: ChatType[] | [],
    idOfLoggedUser: number,
    selectedIds: number[],
    options: (turnedOn: boolean, idMessage: number) => void;
    HeaderSelected: string;
}

export default function RenderMessages({chats, idOfLoggedUser, options, HeaderSelected, selectedIds}: RenderChat){

    return(
        <div
            className="w-[90vw] h-[90vh] h-[90vh]  mx-auto   overflow-y-auto pb-16"
        >
            <div
                className="flex flex-col"
            >
                {chats?.length > 0 &&(
                    chats?.map((c) => {
                        
                        //verify if the message is selected
                        let selectedMessage: boolean = selectedIds.includes(c?.id);

                        //message from user or not
                        const messageFromUser = (idOfLoggedUser === c?.idUserMessage) ? true : false     
                        
                        return(
                            <SkeMessage
                            key={c.id}
                            idMessage={c.id}
                            message={c?.message}
                            time={c?.time}
                            messageFromLoggedUser={messageFromUser}
                            status={c?.status}
                            options={(onOrOf, idMessage) => {
                                if(idMessage){
                                    options(onOrOf, idMessage);
                                }
                                
                            }}
                            HeaderSelected={HeaderSelected}
                            selectedMessage={selectedMessage}
                        />
                        )
                    })
                )}
            </div>
        </div>
    )
}