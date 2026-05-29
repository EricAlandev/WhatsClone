import { UserWithClass } from "@/typescript/types/UserType";
import HeaderUserDetail from "./HeaderUserDetail";

type UserDetail = {
    userData?: UserWithClass;
    isUserDetailOpen: boolean;
    setIsUserDetailOpen: any;
}

export default function UserDetail({userData, isUserDetailOpen, setIsUserDetailOpen} : UserDetail){

    return(
        <>
            <HeaderUserDetail
                userData={userData}
                isUserDetailOpen={isUserDetailOpen}
                setIsUserDetailOpen={setIsUserDetailOpen}
            />
        </>
    )
}