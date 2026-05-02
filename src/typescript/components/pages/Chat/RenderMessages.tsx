'use client'

import { ChatType, selectedIds } from "@/typescript/types/ChatType"
import SkeMessage from "./Message";

type RenderChat = {
    chats: ChatType[] | [],
    idOfLoggedUser: number,
    selectedIds: selectedIds[],
    options: (turnedOn: boolean, idMessage: number, message: string, time: string, status: string) => void;
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
                        let selectedMessage: boolean = false;

                        for(let i = 0; i < selectedIds.length; i++){
                            if(c?.id === selectedIds[i].id){
                                selectedMessage = true
                            }
                        }

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
                            edited={c?.edited}
                            options={(onOrOf, idMessage) => {
                                if(idMessage && c?.message && c?.time && c?.status){
                                    //send the state of selected,   idMessage, message, time, status
                                    options(onOrOf, idMessage, c?.message, c?.time, c?.status);
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