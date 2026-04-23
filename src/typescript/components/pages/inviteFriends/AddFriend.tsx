import useUserCase from "@/typescript/servers/useCases/UserUseCase";
import SearchBar from "../../headers/SearchBar";
import { UserType } from "@/typescript/types/UserType";


export default function AddFriend(){

    const {addFriends} = useUserCase();

    return(
        <div
            className="w-[90vw] mx-auto mt-2"
        >
            <h1
                className=" mt-2 font-semibold text-center text-[15.5px] text-[white]"
            >
                Search for someone in the plataform to add
            </h1>
        </div>
    )
}