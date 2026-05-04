
type UserToAddSke = {
    
    idUser?: number,
    idLoggedUser?: number,
    name?: string,
    descriptionUser?: string,

    add: (id: number ) => void
}

export default function UserToAddSke({idUser, idLoggedUser, name, descriptionUser, add} : UserToAddSke){
    return(
        <>
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

                            {/*Verify if user is you or not */}
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
        </>
    )
}