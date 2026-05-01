'use client'

import { ChatType } from "@/typescript/types/ChatType";
import { useState } from "react";

type Message = ChatType &{
    //message
    message?: string,
    idMessage?: number,
    messageFromLoggedUser?: boolean
    selectedMessage: boolean

    options: (onOrOf: boolean, idMessage: number) => void;
    HeaderSelected: (set: any) => void;
    
}

export default function SkeMessage({
    message, idMessage, messageFromLoggedUser, time,status,selectedMessage , 
    
    options, HeaderSelected
} : Message){

    const [timer, setTimer] = useState<NodeJS.Timeout | null>(null);

    //define the logical of the image photo;
    let urlImage;
    switch(status){
        case"Visualized":
            urlImage = "/messages/Visualized.png"
            break

        case "not viewed":
            urlImage = "/messages/NonVisualized.png"
            break

        default:
            break
    }

    const handleTouchStart = () => {
        // Start a timer for 500ms
        const i = setTimeout(() => {
            if(idMessage){
                options(true, idMessage);
            }
        }, 500);
        setTimer(i);
    };
    
    const handleTouchEnd = () => {
        // If they lift their finger before 500ms, cancel the timer
        if (timer) clearTimeout(timer);
    };


    return(
        <div
            onTouchStart={handleTouchStart}
            onTouchEnd={handleTouchEnd}
            className={`relative flex items-center max-w-[150px] rounded-md gap-4 mt-5 p-2 bg-[#A0A0A0]
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
                        <p className="text-[12px]">
                            {time}
                        </p>

                        <img
                            src={urlImage}
                            className=" max-h-[22px] "
                        />
                    </div>
                </div>
        </div>
    )
}