const TaskEntry = (item) => (
    <li key={item.todo.id} className='flexbox'>
        <div>{"Task " + (item.todo.id)}</div>
        <div>{item.todo.taskdescription}</div>

    </li>
)    

export default TaskEntry;