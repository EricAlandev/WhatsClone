import Link from "next/link"
import ImageStatusMessage from "./ImageStatusMessage";


type SkeListValue = {
    type: "list" | "chat" |  "add" | "message"

    //list type
    idList?: number,
    nameList?: string,
    description?: string,
    image_url?: string,
    code?: number,

    idUser?: number,
    idLoggedUser?: number,
    name?: string,
    descriptionUser?: string,


    //message
    message?: string,
    status?: string,
    time?: string,
    idMessage?: number,
    messageFromLoggedUser?: boolean,

    //functions
    add: (id: number ) => void

}

export default function SkeListValue({
    type,
    image_url,
    nameList,
    description
} : SkeListValue){

    //define the name of the url
    let nameURL : string;
    switch(nameList){
        case"Invite friends":
            nameURL = "inviteFriends";
            break;
        case"Account":
            nameURL = "account";
            break;
    }

    return(
        <div
            className="relative"
        >
            {type === "list" && (
                <Link
                    href={`/configurations/${nameURL}`}
                    className="flex items-center gap-4 mt-5"
                >
                    <img
                        src={image_url}
                        className="max-w-[30px] max-h-[30px]"
                    />

                    <div
                        className="text-[white]"
                    >
                        <p>{nameList}</p>
                        <p>{description}</p>
                    </div>

                </Link>
            )}
        </div>
    )
}