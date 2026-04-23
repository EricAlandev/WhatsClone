'use client'

import { ChangeEvent,FormEvent, useEffect, useState } from "react"

type SearchBar = {
    send: (value: valueInputs) => void;
}

type valueInputs = {
    text: string
}

export default function SearchBar({send} : SearchBar){

    const [valueInput, setValueInput] = useState<valueInputs>({text: ""});

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setValueInput((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault(); 
        send(valueInput)
    };

    useEffect(() => {
        if(valueInput?.text === ""){
            send({text: ""});
        }
        else{
            send(valueInput);
        }
    }, [valueInput]);
    

    return(
        <div
            className="w-[90vw] mx-auto"
        >
            {/*Search Bar */}
            <form
                onSubmit={handleSubmit}
                className="relative mt-3"
            >
                    <img
                        src={"/generals/SearchIcon.png"}
                        className="absolute top-2 left-1.5 max-h-[32px] "
                    />

                    <input
                        name="text"
                        value={valueInput.text}
                        onChange={handleChange}
                        placeholder="Search"
                        className=" w-[90vw] min-h-[45px] mx-auto pl-12.5 bg-[#2E2F2F] text-[17px] rounded-md placeholder:text-[#E2E8F0]"
                    />
            </form>
        </div>
    )
}