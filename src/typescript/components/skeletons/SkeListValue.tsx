import Link from "next/link"
import ImageStatusMessage from "./ImageStatusMessage";


type SkeListValue = {
    //list type
    nameList?: string,
    description?: string,
    image_url?: string,
}

export default function SkeListValue({
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
        </div>
    )
}