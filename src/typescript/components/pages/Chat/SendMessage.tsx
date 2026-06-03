import { MessageType } from "@/typescript/types/ChatType";
import { useState } from "react"

type SendMessage = {
    send: (message: MessageType) => void;
    unBlock: () => void;
    clearMessages: () => void;
    blocked?: boolean;
    userBlockedChat?: boolean;
}

export default function SendMessage({send, unBlock, clearMessages,  blocked, userBlockedChat} : SendMessage){

    const [message, setMessage] = useState<MessageType>({text: ""});

    const handleChanger = async(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;
        setMessage((m) => ({
                ...m, [name] : value
            }))
    }

    return(
        <>
            {/*Verify if is blocked or not */}
            {blocked === false ? (
                <div
                    className="fixed left-1/2 -translate-x-1/2 bottom-3"
                >
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            send(message);

                            //clear the old text
                            setMessage({text: ""});
                        }}
                        className="flex w-[90vw] h-[5vh] bg-[#2E2F2F] rounded-[15px]"
                    >
                        <input
                            name="text"
                            value={message?.text}
                            onChange={handleChanger}
                            placeholder="Message"
                            className="w-[90vw]  pl-15 placeholder:text-[white] rounded-[15px] placeholder:font-medium placeholder:text-[#18191b] text-[white]"
                        />
                    </form>
                </div>
            ) : (
                <>
                    {/*Verify if the user blocked or not*/}
                    {userBlockedChat === true ? (
                        <div className="fixed flex bottom-0 w-full h-[8vh] bg-[#2E2F2F] text-[15px] ">
                            
                            <div
                                className="flex items-center mx-auto gap-4"
                            >
                                {/*ClearMessages Button */}
                                <div
                                    onClick={() => {
                                        clearMessages();
                                    }}
                                    className="flex items-center gap-4 ButtonWhenBlocked"
                                >   
                                    <img
                                        src={"/messages/redTrash.png"}
                                        className="imagePattern"
                                    />
                                    <button
                                        className="font-meidum text-[#FA5252]"
                                    >
                                        Clear Messages?
                                    </button>
                                </div>
                                        
                                {/*UnBlock Button */}
                                <div
                                    onClick={() => {
                                        unBlock();
                                    }}
                                    className="flex items-center gap-4 ButtonWhenBlocked"
                                >
                                    <img
                                        src={"/messages/GreenBlock.png"}
                                        className="imagePattern"
                                    />
                                    <button
                                        className="block mx-auto font-meidum text-[#40C057]"
                                    >
                                        Unblock?
                                    </button>
                                </div>
                            </div>
                        </div>
                        ) : (
                            <>
                            <div className="fixed bottom-0 w-full h-[5vh] bg-[#2E2F2F] ">
                                <p
                                    className="text-center"
                                >
                                    You got blocked
                                </p>
                            </div>
                            </>
                        )}
                </>
            )}
        </>
    )
}