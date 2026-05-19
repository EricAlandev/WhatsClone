import { ChatType } from "@/typescript/types/ChatType";

type DefineHowManyFixed = {
    quantity: ChatType[],
    idFixedMessage: number
}

export default function DefineHowManyFixed({quantity, idFixedMessage}: DefineHowManyFixed){

    //prop who define the height of each fixed message simbol;
    let height = "text-[12px]";

    switch(quantity.length){
        case 1:
         height = "text-[15px]"
         break;

        case 2:
         height = "text-[12px]"
         break;

        case 3:
         height = "text-[8px]"
         break;

        default:
         height = "text-[12px]"
         break
    }

    return(
        <>  
            {quantity?.length > 0 && (
                <div
                    className="flex flex-col gap-4.5 ml-2 mb-5"
                >
                    {quantity?.map((c, index) => {
                        
                        return(
                            <div
                                key={index}
                                className={`max-h-[0.5px]`}
                            >
                                    <p className={`font-extrabold ${height}

                                    ${idFixedMessage == index ? 
                                        "text-[gray]" 
                                        : 
                                        "text-[white]"
                                    }
                                    `}>
                                        |
                                    </p>
                            </div>
                            )
                        })
                    }
                </div>
            )}
        </>
    )
}