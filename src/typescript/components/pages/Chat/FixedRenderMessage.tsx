'use client'

import { ChatType } from "@/typescript/types/ChatType"
import DefineHowManyFixed from "./DefineHowManyFixed";
import { Swiper, SwiperSlide } from "swiper/react";

type FixedRenderMessage = {
    chats : ChatType[] | [];
    idFixedMessage: any,
    SetIdFixedMessage: any;
}

export default function FixedRenderMessage({chats, 
    idFixedMessage, SetIdFixedMessage} : FixedRenderMessage){

    let fixedQuantity : ChatType[] = [];

    //verify if exist fixed messages;
    for(let i = 0; i < chats?.length; i++){
        if(chats[i].fixed === true){
            fixedQuantity.push(chats[i]);
        }
    }

    if(fixedQuantity.length === 0) return null


    return(
        <div
            className=" w-full h-[10vh] max-h-[50px] bg-[#A0A0A0] z-50"
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
                            if(idFixedMessage === index){
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
                                )
                            }
                        })}
                </div>
            )}
        </div>
    )
}