import { ChatType } from "@/typescript/types/ChatType"
import DefineHowManyFixed from "./DefineHowManyFixed";
import { Swiper, SwiperSlide } from "swiper/react";

type FixedRenderMessage = {
    chats : ChatType[] | []
}

export default function FixedRenderMessage({chats} : FixedRenderMessage){

    console.log("Chats fixed render", chats)

    let fixedQuantity : number = 0;

    //verify if exist fixed messages;
    for(let i = 0; i < chats?.length; i++){
        if(chats[i].fixed === true){
            fixedQuantity++;
        }
    }

    if(fixedQuantity === 0) return null

    return(
        <>
            {fixedQuantity > 0 &&(
                <Swiper>
                   {chats.map((c) => (
                    <>
                        <SwiperSlide
                            key={c?.id}
                            className="absolute w-full h-[10vh]  max-h-[45px] bg-[#A0A0A0] z-50"
                        >

                            {/*Message + img */}
                            <div
                                className="flex items-center mt-2"
                            >

                                <DefineHowManyFixed
                                    quantity={fixedQuantity}
                                />

                                {/*render*/}
                                <div
                                    className="flex gap-5"
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
                    </SwiperSlide>
                    </>
                ))}
                </Swiper>
            )}
        </>
    )
}