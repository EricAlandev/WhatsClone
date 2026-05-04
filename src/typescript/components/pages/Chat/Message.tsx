'use client'

import { ChatType } from "@/typescript/types/ChatType";
import { useState } from "react";
import ImageStatusMessage from "../../skeletons/ImageStatusMessage";

type Message = ChatType &{
    //message
    message?: string,
    idMessage?: number,
    messageFromLoggedUser?: boolean
    selectedMessage: boolean,
    edited: boolean

    options: (onOrOf: boolean, idMessage: number) => void;
    HeaderSelected: string;
    
}

export default function SkeMessage({
    message, idMessage, messageFromLoggedUser, time, status,selectedMessage , edited,
    
    options, HeaderSelected
} : Message){

    const [timer, setTimer] = useState<NodeJS.Timeout | null>(null);

    const handleTouchStart = () => {
        //Only let press if was not selected yet and if the message is yours
        if(selectedMessage === false && messageFromLoggedUser === true){
            const i = setTimeout(() => {
                if(idMessage){
                    options(true, idMessage);
                }
            }, 500);
            setTimer(i);
        }
    };
    
    const handleTouchEnd = () => {
        // If they lift their finger before 500ms, cancel the timer
        if (timer) clearTimeout(timer);
    };


    return(
        <div
            onTouchStart={handleTouchStart}
            onTouchEnd={handleTouchEnd}
            className={`relative flex items-center max-w-[150px] rounded-md gap-4 mt-5 p-2 bg-[#144D37] text-[white]
            ${messageFromLoggedUser === true ? 
                `self-end` 
                    : 
                "self-start"//justify-between if for the chields
                            //self is for himself
            }
            ${(HeaderSelected !== "hidden" && messageFromLoggedUser === true && selectedMessage === true)  && "bg-[#F1F1F1]"} `}
        >

                {/*Message and text */}
                <div>
                    <p
                        className="max-w-[130px] wrap-break-word"
                    >
                        {message}
                    </p>

                    {/*Footer part of the messsage */}
                    <div
                        className="flex items-center gap-1.5"
                    >
                        {edited === true && (
                            <p
                                className="text-[14px] border-b-[0.5px]"
                            >
                                edited
                            </p>
                        )}

                        <p className="text-[12px]">
                            {time}
                        </p>

                        <ImageStatusMessage
                            status={status}
                        />
                    </div>
                </div>
        </div>
    )
}