<template>
  <div>

  </div>
</template>
<script>
export default {
  data() {
    return {
        data:'',
    };
  },
  created() {
    this.$store.dispatch("tournament/getAll").then((response) => {
      this.data = response.data;
    });
  },
  methods: {},
};
</script>
 