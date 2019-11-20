import HTTP from './http';

export default {
  findQuestions(text, category) {
    console.log(`service searching questions text=${text} category=${category}`);
    return HTTP.get('/answers', { params: { text, category } });
  }
};
