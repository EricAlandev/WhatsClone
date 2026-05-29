'use client'

import { ChatType } from "@/typescript/types/ChatType"
import DefineHowManyFixed from "./DefineHowManyFixed";
import { useEffect, useState } from "react";

type FixedRenderMessage = {
    chats : ChatType[] | [];

    //manage the actual id fixed(Define what message gonna renderize etc);
    //idFixedMessage is the currently message fixed
    idFixedMessage: any,
    SetIdFixedMessage: any;

    //function that is used to the polling
    idPage: string;
    verifyFixedMessages: any
    token: string | null
}

export default function FixedRenderMessage({chats, 
    idFixedMessage, SetIdFixedMessage , idPage, verifyFixedMessages, token} : FixedRenderMessage){

    //array with just the ids of the fixed messages
    const [arrayWithFixedsIds, setArrayWithFixedsIds] = useState<Number[]>([]);

    let fixedQuantity : ChatType[] = [];

    //verify if exist fixed messages and set when all of tthem was verifyeds;
    for(let i = 0; i < chats?.length; i++){
        if(chats[i].fixed === true){
            fixedQuantity.push(chats[i]);
        }
    }

    const allOfFixedIds : Number[] = fixedQuantity.map(id => id.id);

    if(JSON.stringify(arrayWithFixedsIds) !== JSON.stringify(allOfFixedIds)){
        setArrayWithFixedsIds(allOfFixedIds);
    }

    useEffect(() => {
        let intervalId : any;  
        //if all of the messages are verifyed and the user have token, set the array with the fixedQuantity and after this, if he update, he gonna call the
        if(arrayWithFixedsIds.length > 0 && token){
                intervalId = setInterval(() => verifyFixedMessages(arrayWithFixedsIds, token, idPage), 
                10000);
        }

        return () => {
            if(intervalId){
                clearInterval(intervalId);
            }
        }

    }, [arrayWithFixedsIds, allOfFixedIds])

    
    if(fixedQuantity.length === 0) return null;

    return(
        <div
            className=" w-full h-[10vh] max-h-[50px] bg-[#A0A0A0] z-40"
        >
            {fixedQuantity.length > 0 &&(
                <div
                    className=" flex items-center top-2"
                >
                    <DefineHowManyFixed
                        quantity={fixedQuantity}
                        idFixedMessage={idFixedMessage}
                    />

                        {fixedQuantity.map((c, index) => {
                                if(index === idFixedMessage){
                                return (
                                    <div
                                        key={c?.id}
                                        className=" flex items-center w-full"

                                        //Define the id Of the fixedMessage;
                                        onClick={() => {
                                            //If is the last value set to 0;
                                            if(idFixedMessage + 1 >= fixedQuantity.length ){
                                                SetIdFixedMessage(0);
                                            }

                                            else{
                                                SetIdFixedMessage(idFixedMessage + 1);
                                            }
                                        }}
                                        
                                    >
                                            {/*Message + img */}
                                                <div
                                                    id={`${c?.id}`}
                                                    className="flex gap-5 ml-2"
                                                >
                                                    <img
                                                        src={"/messages/PutFixado.png"}
                                                        className="max-h-[25px] ml-4"
                                                    />

                                                    <p
                                                        className=" text-[white]"
                                                    >
                                                        {c?.message}
                                                    </p>
                                            </div>
                                    </div>
                                )}
                            }
                        )}
                </div>
            )}
        </div>
    )
}