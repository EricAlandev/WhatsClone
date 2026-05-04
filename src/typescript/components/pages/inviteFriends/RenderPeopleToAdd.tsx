'use client'

import { UserType } from "@/typescript/types/UserType"
import SkeListValue from "../../skeletons/SkeListValue";
import { useEffect } from "react";
import useUserCase from "@/typescript/servers/useCases/UserUseCase";
import { useAuth } from "@/typescript/contexts/GlobalContext";
import UserToAddSke from "./UserToAddSke";

type RenderPeopleToAdd = {
    result: UserType[];
}

export default function RenderPeopleToAdd({result} : RenderPeopleToAdd){

    const {addFriend} = useUserCase();
    const {user, token} = useAuth();

    return(
        <div
            className="w-[90vw] mx-auto"
        >  
            {result.length > 0 ? (
                result.map((r) => (
                    <UserToAddSke
                        idUser={r?.id}
                        name={r?.name}
                        descriptionUser={r?.description}
                        add={(id) => {
                            if(id && token){
                                addFriend(id, token);
                            }
                        }}
                        idLoggedUser={user?.id}
                    />
                ))
            ) : (
                <>
                    <p
                        className="mt-5 text-[17px] text-[white] text-center"
                    >
                        No results for you search!
                    </p>
                </>
            )}
        </div>
    )
}