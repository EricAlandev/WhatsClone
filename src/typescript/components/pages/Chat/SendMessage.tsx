import { MessageType } from "@/typescript/types/ChatType";
import { InputHTMLAttributes, useState } from "react"

export default function SendMessage(){

    const [message, setMessage] = useState<MessageType>({text: ""});

    const handleChanger = async(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;
        setMessage((m) => ({
                ...m, [name] : value
            }))
    }

    return(
        <div
            className="fixed left-1/2 -translate-x-1/2 bottom-3"
        >
            <form
                className="flex w-[90vw] h-[5vh] bg-[#2E2F2F] rounded-[15px]"
            >
                <input
                    name="text"
                    value={message?.text}
                    onChange={handleChanger}
                    placeholder="Message"
                    className="w-[90vw]  pl-15 placeholder:text-[white] rounded-[15px] placeholder:font-medium placeholder:text-[#18191b]"
                />
            </form>
        </div>
    )
}