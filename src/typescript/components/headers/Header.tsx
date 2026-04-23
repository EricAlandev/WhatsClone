'use client'

import Link from "next/link";

import SearchBar from "@/typescript/components/headers/SearchBar";

export default function Header(){

    return(
        <div
            className="mx-auto mt-5"
        >
            
            <div
                className="flex justify-between "
            >
                <Link
                    href={"/homepage"}
                    className="text-[18px] text-[#f1f1f1]"
                >
                    WhatsApp
                </Link>
                
                {/*3 points */}
                <Link
                    href={"/configurations"}
                    className="leading-2 font-bold text-[20px] text-[#F1F1F1]"
                >
                    <p>.</p>
                    <p>.</p>
                    <p>.</p>
                </Link>
            </div>

            <SearchBar/>
        </div>
    )
}