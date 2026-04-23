'use client'

import { ConfigsList } from "@/typescript/types/ConfigsListType"
import SkeListValue from "../../skeletons/SkeListValue"


type ListConfigurations = {
    lists: ConfigsList[]
}

export default function ListConfigurations({lists} : ListConfigurations){

    console.log("lists values", lists);
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
                        <SkeListValue
                            type="list"
                            nameList={l?.nameList}
                            description={l?.description}
                            image_url={l?.imageUrl}
                        />
                        
                    </>
                ))
            )}
        </div>
    )
}