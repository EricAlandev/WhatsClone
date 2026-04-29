import { ChatType } from "@/typescript/types/ChatType";

type Message = ChatType &{
    //message
    message?: string,
    idMessage?: number,
    messageFromLoggedUser?: boolean
}

export default function SkeMessage({message, idMessage, messageFromLoggedUser, time,status } : Message){

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


    return(
        <div
            className={` flex items-center max-w-[150px] rounded-md gap-4 mt-5 p-2 bg-[#A0A0A0] ${messageFromLoggedUser === true ? 
                `self-end` 
                    : 
                "self-start"//justify-between if for the chields
                            //self is for himself
            }
            `}
        >

                {/*Message and text */}
                <div
                    
                >
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