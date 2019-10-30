<template>
  <div class="section">
    <div class="container">
      <div class="wrapper">
        <figure class="image">
          <router-link :to="{name: 'home'}">
            <img src="explainlikeimfive.png">
          </router-link>
        </figure>
        <div class="field has-addons has-addons-centered">
          <p class="control">
            <span class="select">
              <select>
                <option>All categories</option>
                <option>Mathematics</option>
                <option>Economics</option>
                <option>Culture</option>
                <option>Biology</option>
                <option>Chemistry</option>
                <option>Physics</option>
                <option>Technology</option>
                <option>Engineering</option>
                <option>Other</option>
              </select>
            </span>
          </p>
          <p class="control is-expanded">
            <input class="input" type="text" placeholder="Ask a question">
          </p>
          <p class="control">
            <a class="button is-primary">
              Search
            </a>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home'
};
</script>

<style lang="scss" scoped>
.section, .container, .wrapper {
  height: 100%;
}
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.image {
  max-width: 400px;
}

.field {
  min-width: 60%;
}

</style>
