<template>
  <div>
    <b-table
      id="my-table"
      striped
      hover
      :items="rank"
      small
      :fields="fields"
      bordered
      fixed
      class="text-center"
    >
      <template v-slot:cell(No)="row">
        {{ rank.indexOf(row.item) + 1 }}
      </template>
      <template v-slot:cell(logo)="row">
        <v-img
          max-height="60"
          max-width="80"
          :src="row.item.team.logo"
        ></v-img>
      </template>
      <template v-slot:cell(rank)="row">
        {{row.item.rank * 100}}%
      </template>
      <template v-slot:cell(Detail)="row">
        <a
          :href="
            $router.resolve({ path: '/edit/' + row.item.idTeam }).href
          "
        >
          <b-icon icon="chevron-double-right"></b-icon></a
        >

       
      </template>
    </b-table>
  </div>
</template>
<script>
export default {
  props: {
    idTour: String,
  },
  data() {
    return {
      rows: "",
      rank: [],
      fields: [
        "No",
        {
          key: "name",
          label: "Team",
        },
        {
          key: "logo",
          label: "Logo",
        },
        {
          label: "Mp",
          key: "team.totalmatch",
        },
        {
          key: "team.totalwin",
          label: "Win",
        },
        {
          key: "totalGoals",
          label: "Total Goals",
        },
         {
          key: "rank",
          label: "Win rate",
        },
        ,
        "Detail",
      ],
    };
  },

  created() {
    this.$store
      .dispatch("tournament/getRankByTour", this.$route.params.id)
      .then((response) => {
        this.rank = response.data;
        this.rows = this.rank.length;
        // console.log(this.rank);
      });
  },
};
</script>