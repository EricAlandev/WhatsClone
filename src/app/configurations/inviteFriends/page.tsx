'use client'

import AuthorizationComponent from "@/typescript/components/authorizations/AuthorizationComponent";
import SearchBar from "@/typescript/components/headers/SearchBar";
import AddFriend from "@/typescript/components/pages/inviteFriends/AddFriend";
import HeaderInviteFriends from "@/typescript/components/pages/inviteFriends/HeaderInviteFriends";
import RenderPeopleToAdd from "@/typescript/components/pages/inviteFriends/RenderPeopleToAdd";
import useUserCase from "@/typescript/servers/useCases/UserUseCase";

export default function InviteFriendsPage(){

    const {values, searchPeopleToAdd} = useUserCase();

    return(
        <div
            className="flex flex-col  min-h-screen pb-2 bg-[#161717]"
        >
            <AuthorizationComponent>
                <HeaderInviteFriends/>
                <AddFriend/>

                <SearchBar
                    send={(s) => {
                        searchPeopleToAdd(s?.text)
                    }}
                />

                <RenderPeopleToAdd
                    result={values}
                />
            </AuthorizationComponent>
        </div>
    )
}