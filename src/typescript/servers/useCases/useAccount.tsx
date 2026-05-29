import { UserType } from "@/typescript/types/UserType"
import { changeAccountService } from "../ServiceAccount"


export default function useAccount(){

    const changeAccount = async (user: UserType, token: string) => {
        try{
            const change = await changeAccountService(user, token);

            return change;
        }

        catch(error: any){

        }
    }

    return {changeAccount}
}