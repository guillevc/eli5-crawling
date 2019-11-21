import HTTP from './http';

export default {
  findAnswers(text, category, page, size) {
    console.log(`service searching questions text=${text} category=${category} page=${page} size=${size}`);
    return HTTP.get('/answers', {
      params: {
        text, category, page, size
      }
    });
  }
};
