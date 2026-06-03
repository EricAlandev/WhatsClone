'use client'
import useChatUseCase from "@/typescript/servers/useCases/useChatUseCase";
import { ConfigsList } from "@/typescript/types/ConfigsListType";


type SkeListWithFunctions = ConfigsList & {
    //props to make the fetch
    idChat: string;
    token: string;
}

export default function SkeListWithFunctions({
    imageUrl,
    nameList,

    idChat,
    token
    
} : SkeListWithFunctions){

    const {blockUser, deleteChat, cleanMessages, Denunce} = useChatUseCase();

    return(
        <div
            onClick={() => {
                if(idChat && token){
                    blockUser(idChat, token);
                }
            }}
            className="flex items-center gap-4 mt-5"
        >
            <img
                src={imageUrl}
                className="max-w-[30px] max-h-[30px]"
            />

            <div
                className="text-[white]"
            >
                <p>{nameList}</p>
            </div>
        </div>
    )
}