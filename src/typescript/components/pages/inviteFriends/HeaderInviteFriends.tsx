'use client'

import Link from "next/link";

type HeaderConfig = {
    name?: string
}

export default function HeaderInviteFriends( {name} : HeaderConfig){

    return(
        <div
            className=" w-[90vw] mx-auto mt-5"
        >   
            {/*Back and SearchIcon */}
            <div
                className="flex items-center justify-between"
            >
                <Link
                    href={"/configurations"}
                >
                    <img
                        src={"/generals/Back.png"}
                        className="w-[32px] h-[32px]"
                    />
                </Link>
            </div>
        </div>
    )
}