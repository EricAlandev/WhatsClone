import { UserType } from "../types/UserType";


export const changeAccountService = async (user: UserType, token: string) => {

    try{
        const request = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/options/account`, {
            method: "PUT",
            headers: {
                "Content-type" : "application/json",
                "Authorization" : `Bearer ${token}`
            },
            body: JSON.stringify(user)
        })

        if(!request.ok){
            throw new Error("FAIL");
        }

        const response = request.json();

        return response;
    }

    catch(error: any){

    }
}