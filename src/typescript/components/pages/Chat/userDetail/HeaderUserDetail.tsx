import { UserWithClass } from "@/typescript/types/UserType";

type UserDetail = {
    userData?: UserWithClass;
    isUserDetailOpen: boolean;
    setIsUserDetailOpen: any;
}

export default function HeaderUserDetail({userData, isUserDetailOpen, setIsUserDetailOpen} : UserDetail){

    const numberFirstPart = userData?.number?.number?.substring(0,4);

    const numberSecondPart = userData?.number?.number?.substring(5,9);

    const phoneNumber = `+55 ${userData?.number.ddd} ${numberFirstPart}-${numberSecondPart}`

    return(
        <>
                    <img
                      src={"/messages/close.png"}
                      onClick={() => {
                        setIsUserDetailOpen(false);
                      }}
                    />
                    
                    {/*Body of the UserDetail */}
                    <div
                        className=" text-[white]"
                    >

                        <img
                            src={"/messages/UserPhoto.png"}
                            className="mx-auto h-[5vh] mt-5 rounded-[50%]"
                        />

                        {/*name + number */}
                        <div
                            className="text-center font-medium"
                        >
                            <p
                                className="mt-3 text-[18px] "
                            >
                                    {userData?.name}
                            </p>
                                
                            <p
                                    className="text-[18px] "
                            >
                                    {phoneNumber}
                            </p>
                        </div>

                        {/*Description */}
                        <div>
                            <p>Recado</p>

                            <p
                                className="font-m text-[20px]"
                                >
                                    {userData?.description}
                            </p>
                        </div>
                    </div>
        </>
    )
}