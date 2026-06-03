import { UserWithClass } from "@/typescript/types/UserType";
import HeaderUserDetail from "./HeaderUserDetail";
import RenderUserDetailOptions from "./RenderUserDetailOptions";
import { ConfigsList } from "@/typescript/types/ConfigsListType";

type UserDetail = {
    userData?: UserWithClass;
    isUserDetailOpen: boolean;
    setIsUserDetailOpen: any;

    //props of actions in the function
    options: ConfigsList[];
    blockUser: any;
    cleanMessages: any;
    deleteChat: any;
    Denunce: any;

    //props to make the fetch
    idChat: string;
    token: string;
}

export default function UserDetail({userData, options, isUserDetailOpen, setIsUserDetailOpen , idChat, token, blockUser , cleanMessages, deleteChat, Denunce} : UserDetail){

    return(
        <>
            {isUserDetailOpen === true && (
                <div
                className="fixed w-full h-screen bg-[#161717] z-50 pt-5 pl-5"
                >
                    <HeaderUserDetail
                        userData={userData}
                        isUserDetailOpen={isUserDetailOpen}
                        setIsUserDetailOpen={setIsUserDetailOpen}
                    />

                    <RenderUserDetailOptions
                        idChat={idChat}
                        token={token}
                        options={options}
                        blockUser={blockUser}
                        cleanMessages={cleanMessages}
                        deleteChat={deleteChat}
                        Denunce={Denunce}

                    />
                </div>
            )}
        </>
    )
}