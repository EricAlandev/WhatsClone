
'use client'

import { MessageType, selectedIds } from "@/typescript/types/ChatType"
import { useSearchParams } from "next/navigation"
import React, { useState } from "react"
import ImageStatusMessage from "../../skeletons/ImageStatusMessage"

type EditMessagePopUp = {
    open: boolean
    setIsModalEdit:  any,
    selectedIds: selectedIds[],

    edit: (text: string) => void
}

export default function EditMessagePopUp({open, setIsModalEdit,selectedIds, edit} : EditMessagePopUp){

    const [message, setMessage] = useState<MessageType>({text: ""});

    const handleChanger = async(e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setMessage((e) => (
            {
                ...e, [name] : value
            }
        ))
    }

    if(!open) return null;

    //The selectedIds just gonna have 1 array with the data of the actual message. So its okay to pick the value 0;
    const selectedMessageValues = selectedIds[0];

    return(
        <>
            <div 
                onClick={() => {
                    setIsModalEdit(false);
                }}
                className="fixed inset-0 bg-[black] opacity-70"
            ></div>

            <div className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[85vw] h-[50vh] max-h-[250px] bg-[white] rounded-md">
                
                {/*Header popUp */}
                <header
                    className="flex items-center gap-2 pt-1.5 pl-3 pb-1.5 bg-[#161717] h-[60px]"
                >
                    {/*Close buttom */}
                    <img
                        src={"/messages/close.png"}
                        onClick={() => {
                            setIsModalEdit(false);
                        }}
                        className="max-w-[28px] max-h-[28px]"
                    />

                    <h2 className="font-medium text-[16.5px] text-[white]">Edit message</h2>
                </header>

                {/*Body popUp */}
                <div
                    className="relative h-full bg-[#2B2D2D]"
                >
                    {/*Message data */}
                    <div
                    className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col max-w-[150px] rounded-md p-2 bg-[#144D37] text-[#F1F1F1]"
                    >
                        <p>{selectedMessageValues?.message}</p>
                        
                        <div
                            className="flex items-center gap-2"
                        >
                            <p className="text-[13.5px]">{selectedMessageValues?.time}</p>

                            <ImageStatusMessage
                                status={selectedMessageValues.status}
                            />
                        </div>

                    </div>
                </div>
                
                {/*Footer popUp */}
                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                        edit(message?.text)
                        setMessage(() => (
                            {text : ""}
                        ))
                    }}
                    className="relative w-full h-[60px] bg-[#161717]"
                >
                    <input
                        name="text"
                        value={message?.text}
                        onChange={handleChanger}
                        placeholder="Type a message"
                        className="w-[91%] mt-3 ml-5 pb-3  text-[white]  placeholder:text-[white] border-[#21C063] border-b-[2px] focus:outline-0"
                    />

                    <button
                        type="submit"
                    >
                        <img
                            src={"/messages/NonVisualized.png"}
                            className="absolute top-2 right-3 max-h-[35px] p-2 bg-[#21C063] rounded-[50%]"
                        />
                    </button>
                </form>
            </div>
        </>
    )
}