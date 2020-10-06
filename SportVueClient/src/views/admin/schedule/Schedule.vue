<template>
  <b-container fluid>
    <v-card v-if="this.loading == true">
      <v-card-title>
        Schedule
        <v-spacer> </v-spacer>

        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-btn class="mx-2" fab dark color="indigo" @click.stop="dialog = true">
        <v-icon dark> mdi-plus </v-icon>
      </v-btn>
      <v-data-table :headers="headers" :items="desserts" :search="search">
        <template v-slot:item="row">
          <tr>
            <td>{{ row.item.title }}</td>
            <td>{{ row.item.nameTour }}</td>

            <td>
              {{ row.item.timeStart }}<v-icon>mdi-forward</v-icon
              >{{ row.item.timeEnd }}
            </td>
            <td>
              {{ row.item.team[0].nameTeam }}<v-icon>mdi-fencing </v-icon
              >{{ row.item.team[1].nameTeam }}
            </td>
            <td>
              {{ row.item.scoreTeam1 != 0 ? row.item.scoreTeam1 : "?" }}-{{
                row.item.scoreTeam2 != 0 ? row.item.scoreTeam2 : "?"
              }}
            </td>
            <td v-if="row.item.status == 0" style="color: Blue">not</td>
            <td v-if="row.item.status == 1" style="color: red">doing</td>
            <td v-if="row.item.status == 2" style="color: Gray">end</td>
            <td>
              <router-link
                :to="{ path: '/DetailSchedule/' + row.item.idSchedule }"
                id="${schedule.idSchedule}"
              >
                <v-btn fab small>
                  <v-icon>mdi-forward</v-icon>
                </v-btn>
              </router-link>
              <v-btn
                v-if="row.item.status == 0"
                class="mx-2"
                fab
                small
                @click="editSchedule(row.item)"
              >
                <v-icon dark>mdi-pencil</v-icon>
              </v-btn>
              <v-btn
                v-if="row.item.status == 0"
                class="mx-2"
                fab
                small
                @click="deleteSchedule(row.item)"
              >
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
      <b-modal id="modal-1" title="Delete" @ok="handleOk()">
        <p class="my-4">Are you sure!</p>
      </b-modal>

      <v-dialog v-model="dialog" max-width="1000px">
        <v-card>
          <v-card-title class="headline"> Add </v-card-title>

          <v-card-text>
            <AddSchedule
              :hideModal="hideModal"
              :getData="getData"
            ></AddSchedule>
          </v-card-text>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialogEdit" max-width="1000px">
        <v-card>
          <v-card-title class="headline"> Edit </v-card-title>

          <v-card-text>
            <EditSchedule
              :hideModal="hideModal"
              :getData="getData"
              :dataEdit="dataEdit"
            ></EditSchedule>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-card>
  </b-container>
</template>
<script>
import AddSchedule from "./AddSchedule";
import EditSchedule from "./EditSchedule";

export default {
  components: {
    EditSchedule,
    AddSchedule,
  },
  data() {
    return {
      dataEdit: {},
      loading: false,
      index: "",
      search: "",
      idDelete: "",
      dialog: false,
      dialogEdit: false,
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: true,
          value: "title",
        },
        { text: "Tournament", value: "nameTour" },
        { text: "Time", value: "timeStart" },
        { text: "Team", value: "type", sortable: false },
        { text: "score", sortable: false },
        { text: "status", value: "status" },
        { text: "action", sortable: false },
      ],
      desserts: [],
    };
  },
  created() {
    this.$store.dispatch("schedule/statusNow").then((response) => {
      this.loading = true;
      this.getData();
    });
  },
  methods: {
    hideModal() {
      this.dialog = false;
      this.dialogEdit = false;
    },
    deleteSchedule(id) {
      this.$bvModal.show("modal-1");
      this.index = this.desserts.indexOf(id);
      this.idDelete = id.idSchedule;
    },
    handleOk() {
      this.$store
        .dispatch("schedule/deleteSchedule", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.desserts.splice(this.index);
          this.getData();
        });
    },
    editSchedule(id) {
      this.dataEdit = id;
      this.dialogEdit = true;
    },
    getData() {
      this.$store.dispatch("schedule/getAll").then((response) => {
        this.desserts = response.data;
      });
    },
  },
};
</script>
