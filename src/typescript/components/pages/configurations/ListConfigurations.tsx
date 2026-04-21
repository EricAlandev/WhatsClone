'use client'

import { ConfigsList } from "@/typescript/types/ConfigsListType"


type ListConfigurations = {
    lists: ConfigsList[]
}

export default function ListConfigurations({lists} : ListConfigurations){

    return(
        <div
            className="w-[90vw] mx-auto mt-5"
        >
            <h2
                className="text-[#A0A0A0]"
            >
                Configurations
            </h2>

            {lists?.length > 0 && (
                lists?.map((l) => (
                    <>

                    </>
                ))
            )}
        </div>
    )
}