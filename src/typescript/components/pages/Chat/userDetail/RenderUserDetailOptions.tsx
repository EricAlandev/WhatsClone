
import SkeListWithFunctions from "@/typescript/components/skeletons/SkeListWithFunctions"
import { ConfigsList } from "@/typescript/types/ConfigsListType"

type RenderUserDetailOptions = {
    options: ConfigsList[];
    
    //props to make the fetch of the options;
    idChat: string;
    token: string;
}

export default function RenderUserDetailOptions({options , idChat, token} : RenderUserDetailOptions){

    return(
        <>
            {options.length > 0 && (
                options.map((o) => (
                        <SkeListWithFunctions
                            idChat={idChat}
                            token={token}
                            nameList={o.nameList}
                            imageUrl={o.imageUrl}
                        /> 
                ))
            )}
        </>
    )
}