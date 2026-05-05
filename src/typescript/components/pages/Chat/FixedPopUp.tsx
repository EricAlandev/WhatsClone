import { selectedIds } from "@/typescript/types/ChatType"
import React, { useState } from "react";


type FixedPopUp = {
    openPopUp?: any
    setIsModalFixed?: any

    fixMessage: (time?: string) => void;
}


type TimeFixed = {
    time?: string
}

export default function FixedPopUp({openPopUp, setIsModalFixed , fixMessage} : FixedPopUp){

    const [message, setMessage] = useState<TimeFixed>({time: ""});

    const handleChanger = async(e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setMessage((e) => (
            {
                ...e, [name] : value
            }
        ))
    }

    if (openPopUp === false) return null

    return(
        <>
            {/*Overlay */}
            <div 
                onClick={() => {
                    setIsModalFixed(false);
                }}
                className="fixed inset-0 bg-[black] opacity-70"
            ></div>

            {/*Body content */}
            <div className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[85vw] h-[50vh] max-h-[260px] pt-5 pl-5  bg-[white] rounded-md">
                
                {/*Tittle */}
                <h1
                    className="max-w-[320px] text-[20px]"
                >
                    Define how long your message will be fixed
                </h1>

                <h2
                    className="mt-1 text-[15px]"
                >
                    You can unfix the message whatever you want
                </h2>

                {/*Form */}
                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                        fixMessage(message.time)
                    }}
                    className="flex flex-col gap-2 mt-2"
                >
                    
                    <div className="flex gap-2">
                        <input
                            id="24h"
                            type="radio"
                            name="time"        
                            value="24h"       
                            onChange={handleChanger}
                            checked={message.time === "24h"}
                        />
                        <label htmlFor="24h">24 hours</label>
                    </div>

                    <div className="flex gap-2">
                        <input
                            id="7d"
                            type="radio"
                            name="time"       
                            value="7d"          
                            onChange={handleChanger}
                            checked={message.time === "7d"}
                        />
                        <label htmlFor="7d">7 Days</label>
                    </div>

                    <div className="flex gap-2">
                        <input
                            id="30days"
                            type="radio"
                            name="time"       
                            value="30d"       
                            onChange={handleChanger}
                            checked={message.time === "30d"}
                        />
                        <label htmlFor="30days">30 Days</label>
                    </div>

                    {/*Buttoms */}
                    <div
                        className="flex gap-2 mr-2 self-end"
                    >
                        <button
                            onClick={() => {
                                setIsModalFixed(false)
                            }}
                            className="w-[20vw] max-w-[120px] "
                        >
                            Cancel
                        </button>

                        <button
                            onClick={() => {
                                fixMessage()
                            }}
                            className="w-[20vw] max-w-[120px] p-2 bg-[#A1A1A1] rounded-md"
                        >
                            Fix
                        </button>
                    </div>
                </form>
            </div>
        </>
    )
}