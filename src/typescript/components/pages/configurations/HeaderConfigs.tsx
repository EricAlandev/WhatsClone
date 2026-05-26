'use client'

import { motion, AnimatePresence } from "framer-motion";
import Link from "next/link";
import React, { useEffect, useState } from "react";

type HeaderConfig = {
    name?: string,
    searchProp: (text: string) => void;
}

export default function HeaderConfig( {name, searchProp} : HeaderConfig){

    const [search, setSearch] = useState({text: ""});

    //Gonna define if the icon got clicked or not
    const [clicked, setClicked] = useState<boolean>(false);

    const handleChanger = (e: React.ChangeEvent<HTMLSelectElement | HTMLInputElement>) => {
        const {name, value} = e.target;

        setSearch((prop) => ({
            ...prop, [name] : value
        }))
    }

    useEffect(() => {
            searchProp(search?.text);
    }, [search])

    return(
        <div
            className=" w-[90vw] mx-auto mt-5"
        >   
            {/*Back and SearchIcon */}
            <div
                className="flex items-center justify-between"
            >
                <Link
                    href={"/homepage"}
                >
                    <img
                        src={"/generals/Back.png"}
                        className="w-[32px] h-[32px]"
                    />
                </Link>

                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                    }}
                    className="flex"
                >
                    <AnimatePresence>
                        {clicked === true && (
                            <>
                                <motion.input
                                    name="text"
                                    value={search.text}
                                    onChange={handleChanger}
                                    initial={{x: 50, opacity: 0}}
                                    animate={{x: 0, opacity: 1}}
                                    exit={{x: 50, opacity: 0}}
                                    className="w-[65vw] h-[35px] p-2 bg-[#A0A0A0] rounded-l-sm text-[#F1F1F1]"
                                />
                            </>
                        )}
                    </AnimatePresence>

                    {/*Search Bar Icon */}
                    <img
                        onClick={() => {
                            setClicked(!clicked);
                        }}
                        src={"/generals/SearchIcon.png"}
                        className={`w-[35px] h-[35px] rounded-r-sm 
                                ${clicked === true && "p-0.5 bg-[#8888]"}
                                duration-300
                            `}
                    />
                </form>
            </div>

            {/*Profile picture + name - optional*/}
                <div
                className="flex flex-col items-center mt-5 gap-2"
            >
                <img
                    src={"/configurationsPage/genericProfilePicture.png"}
                    className="w-[18vw]"
                />

                <p
                    className="text-[18px] text-[white] text-center"
                >
                    {name}
                </p>
            </div>
        </div>
    )
}