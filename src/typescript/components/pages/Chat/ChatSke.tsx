import { selectedIds } from "@/typescript/types/ChatType"
import Link from "next/link"
import ImageStatusMessage from "../../skeletons/ImageStatusMessage"


type MessageLastMessage = selectedIds & {
    idUser?: number,
    name?: string
}

export default function ChatSke({idUser, name, message, time, status} : MessageLastMessage){
    
    return(
        <>
                <Link
                    href={`/chat/${idUser}`}
                    className="relative flex items-center gap-4 mt-5"
                >
                    <img
                        src={"/messages/UserPhoto.png"}
                        className="w-[40px] h-[40px] p-1 bg-[#A0A0A0] rounded-[50%]"
                    />

                    <div
                        className="text-[white]"
                    >
                        <p>{name}</p>

                        
                        {/*Last message */}
                        <div
                            className="flex items-center gap-2 mt-2"
                        >
                            <ImageStatusMessage
                                status={status}
                            />
                            <p>{message}</p>
                        </div>


                        {/*Time */}
                        <p
                            className="absolute top-0 right-0"
                        >
                            {time}
                        </p>
                    </div>
                </Link>
        </>
    )
}