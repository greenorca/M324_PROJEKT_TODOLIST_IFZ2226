import { render, screen } from '@testing-library/react';
import App from './App';
//import { TaskEntry } from './TaskEntry';

/*test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});*/

test('renders ToDo Liste title', () => {
  render(<App />);
  const linkElement = screen.getByText("ToDo Liste");
  expect(linkElement).toBeInTheDocument()
  expect(linkElement).toBeVisible()
  expect(linkElement).toHaveClass("headline")
});
/*
test('renders ToDo item', () => {
    const todo = { taskdescription: "Hello Task",
            id: 101 }

    render(<TaskEntry todo= {todo} index={1000} />);
    const linkElement = screen.getByText(/Hello Task/i);
    expect(linkElement).toBeInTheDocument()
    expect(linkElement).toBeVisible()

    const doneButton = document.getElementsByTagName("button")[0];
    expect(doneButton).toBeInTheDocument();
});
*/