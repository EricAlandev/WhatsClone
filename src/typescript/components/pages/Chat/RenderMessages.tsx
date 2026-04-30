'use client'

import { ChatType } from "@/typescript/types/ChatType"
import SkeListValue from "../../skeletons/SkeListValue";
import SkeMessage from "./Message";
import { useState } from "react";

type RenderChat = {
    chats: ChatType[] | [],
    idOfLoggedUser: number,
    selectedIds: number[],
    options: (turnedOn: boolean, idMessage: number) => void;
    HeaderSelected: (set: any) => void;
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

                        let selectedMessage: boolean = false;

                        //verify if the message is selected
                        for(let i = 0; i < selectedIds?.length; i++){
                            if(c?.id === selectedIds[i]){
                                selectedMessage = true;
                            }
                        }

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