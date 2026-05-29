import { UserType, UserWithClass } from "@/typescript/types/UserType";
import Link from "next/link";
import React, { useState } from "react";

type HeaderAccount = {
    send: (dataForm : UserWithClass) => void;
}

export default function HeaderAccount({send} : HeaderAccount){

    const [data, setData] = useState<UserWithClass>({
        name: "", 
        email: "", 
        password: "", 
        birthday: "", 
        description: "", 
        nacionality: {
            city: "", 
            country: "", 
        },
        number: {
            ddd: "", 
            number: ""
        }
    });

    // Fully wired up handleChanger
    const handleChanger = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;
        setData((d) => ({
            ...d, 
            [name] : value
        }));
    };

    // Form submission interceptor to handle the 'send' callback pipeline
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        send(data);
    };

    return(
        <div className="w-[90vw] mx-auto mt-5">
           <Link href={"/configurations"}>
                <img
                    src={"/generals/Back.png"}    
                    className="w-[32px] h-[32px]"
                    alt="Back"
                /> 
           </Link>

           <form onSubmit={handleSubmit} className=" w-[85vw] mx-auto mt-5">
                <p className=" text-center text-[18px] text-[white]">
                    Changing Data
                </p>
                
                {/*Personal data - Father fieldset */}
                <fieldset className="flex flex-col gap-2 pt-10">
                    <div className="flex gap-7">
                        <div className="flex flex-col">
                            <label className="label">Name</label>
                            <input
                                name="name"
                                value={data.name}
                                onChange={handleChanger}
                                className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            />
                        </div>

                        <div className="flex flex-col">
                            <label className="label">Email</label>
                            <input
                                type="email"
                                name="email"
                                value={data.email}
                                onChange={handleChanger}
                                className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            />
                        </div>
                    </div>

                    <div className="flex gap-7">
                        <div className="flex flex-col">
                            <label className="label">Birthday</label>
                            <input
                                type="datetime-local"
                                name="birthday"
                                value={data.birthday}
                                onChange={handleChanger}
                                className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            />
                        </div>

                        <div className="flex flex-col">
                            <label className="label">Description</label>
                            <input
                                type="text"
                                name="description"
                                value={data.description}
                                onChange={handleChanger}
                                className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            />
                        </div>
                    </div>
                </fieldset>

                {/*Number data - Father fieldset */}
                <fieldset className="flex gap-5 mt-2">
                    <div>
                        <label className="label">DDD</label>
                        <input
                            type="text"
                            name="ddd"
                            value={data.number.ddd}
                            onChange={handleChanger}
                            className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            minLength={2}
                            maxLength={2}
                        />
                    </div>

                    <div>
                        <label className="label">Number</label>
                        <input
                            type="text"
                            name="number"
                            value={data.number.number}
                            onChange={handleChanger}
                            className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            minLength={9}
                            maxLength={9}
                        />
                    </div>
                </fieldset>

                {/*Address data - Father fieldset */}
                <fieldset className="flex gap-5 mt-2">
                    <div>
                        <label className="label">City</label>
                        <input
                            type="text"
                            name="city"
                            value={data.nacionality.city}
                            onChange={handleChanger}
                            className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            maxLength={25}
                        />
                    </div>

                    <div>
                        <label className="label">Country</label>
                        <input
                            type="text"
                            name="country"
                            value={data.nacionality.country}
                            onChange={handleChanger}
                            className="max-w-[35vw] mt-3 bg-[#A0A0A0] rounded-md px-2 py-1 outline-none text-black"
                            maxLength={50}
                        />
                    </div>
                </fieldset>

                {/*Sender */}
                <button 
                    type="submit"
                    className="w-[76.5vw] mt-6 bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 rounded-md transition-all duration-200 shadow"
                >
                    Save Changes
                </button>
           </form>
        </div>
    )
}