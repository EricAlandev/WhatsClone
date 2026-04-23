import Link from "next/link"


type SkeListValue = {
    type: "list" | "chat" |  "add"

    //list type
    idList?: number,
    nameList?: string,
    description?: string,
    image_url?: string,
    code?: number,

    idUser?: number,
    idLoggedUser?: number,
    name?: string,
    descriptionUser?: string

    //functions
    add: (id: number ) => void

}

export default function SkeListValue({
    type,
    idList,
    image_url,
    nameList,
    description,

    idUser,
    idLoggedUser,
    name,
    descriptionUser,

    add

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
        <>
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

            {type === "add" && (
                <div
                    className="relative flex items-center w-[90vw] min-h-[50px] mt-5 p-3 gap-3 "
                >
                    <img
                        src={"/configurationsPage/genericProfilePicture.png"}
                    />

                    <div
                        className="flex items-center text-[white]"
                    >
                        {/*Name & Description */}
                        <div>
                            <p
                                className=""
                            >
                                {name}
                            </p>

                            <p>
                                {descriptionUser}
                            </p>
                        </div>

                        {idLoggedUser !== idUser ? (
                            <button
                            onClick={() => {
                                if(idUser){
                                    add(idUser);
                                }
                            }}
                            className="absolute right-5 min-w-[80px] p-2 bg-[#25D366] font-medium text-[black] border-[#000111] border-[2px] rounded-md cursor-pointer"
                            >
                                Add
                            </button>
                        ) : (
                            <>
                                <button
                                    className="absolute right-5 min-w-[80px] p-2 bg-[#25D366] font-medium text-[black] border-[#000111] border-[2px] rounded-md cursor-pointer"
                                >
                                    You
                                </button>
                            </>
                        )}
                    </div>

                </div>
            )}
        </>
    )
}