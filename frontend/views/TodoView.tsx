import {useEffect, useState} from "react";
import {TodoEndPoint} from "Frontend/generated/endpoints";
import {TextField} from "@hilla/react-components/TextField.js";
import {Button} from "@hilla/react-components/Button.js";
import {Checkbox} from "@hilla/react-components/Checkbox";
import Todo from "Frontend/generated/com/example/application/Todo";

export function TodoView(){
    const [todos,setTodos]=
        useState<Todo[]>([]);
    const [task,setTask]=useState('');
    useEffect(()=>{
        TodoEndPoint.findAll().then(setTodos);
    },[]);

    async function addTodo(){
     const saved=  await TodoEndPoint.add(task);
     if(saved){
         setTodos([...todos,saved]);
         setTask('');
     }

    }
   async function updateTodo(todo:Todo,done:boolean){
        const update= await  TodoEndPoint.update({
            ...todo,done
        });
        if(update){
            setTodos(todos.map(
                exisiting =>
                    exisiting.id===update.id?update:exisiting));
        }


    }
    return(
        <div>
            <h1>Hilla cool todo!</h1>
            <div className='flex gap-s'>

                <TextField value={task} onChange={e=>setTask(e.target.value)} />
                <Button theme='primary' onClick={addTodo}>Add</Button>
            </div>
            {todos.map(todo=>(
                <div key={todo.id}>

                    <Checkbox checked={todo.done} onCheckedChanged={e=>updateTodo(todo,e.detail.value)}/>
                    <span>{todo.task}</span>
                </div>
            ))}
        </div>
    )
}